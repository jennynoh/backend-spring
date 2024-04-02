# package import : fastapi, uvicorn, pandas, numpy
import pickle
import pandas as pd 
import numpy as np 

from fastapi import FastAPI
import uvicorn

from pydantic import BaseModel # 꽃잎의 .... 을 저장할 수 있는 모델
from starlette.responses import JSONResponse # json을 반환하기 위한 패키지

# Model 생성 
class Item(BaseModel) :
    petalLength: float
    petalWidth: float
    sepalLength: float
    sepalWidth: float

# app 개발
app = FastAPI()

# app을 통해서 받을 데이터 (from spring) - post
@app.post(path="/items", status_code=201)

def myiris(item : Item) :
    # pickle 파일 로딩
    with open('data.pickle', 'rb') as f:
        model = pickle.load(f)
        dicted = dict(item)
    
        petalLength = dicted['petalLength']
        petalWidth = dicted['petalWidth']
        sepalLength = dicted['sepalLength']
        sepalWidth = dicted['sepalWidth']

        target = ['setosa', 'versicolor', 'virginica']
        ary = np.array([[sepalLength, sepalWidth, petalLength, petalWidth]])

        pred = model.predict(ary)
        result = {'predict_result': target[pred[0]]}
        
        print("=====예측반환값======", pred)
        print("=====예측결과값======", result)
    
    return JSONResponse(result)

# 포트번호를 주고 uvicorn으로 구동시키기 
if __name__ == '__main__' :
    uvicorn.run(app, host="127.0.0.1", port=8000)
