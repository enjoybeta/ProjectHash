from flask import Flask,request
import json
import datetime
from apscheduler.schedulers.background import BackgroundScheduler
from search1_serving import search_serving
from search2_keyword import search_keyword
from user_manager import sign_up,login,upload,download
from random_today import random_today

todayStr1 = " "
todayStr2 = " "
todayStr3 = " "
todayStr4 = " "
print("Init today's suggestions")

app = Flask(__name__)

@app.route("/")
def guidance():
    return "/today1 /today2 /today3 /today4 /search"

@app.route("/today1")
def today1():
    print("["+todayStr1+"]")
    return todayStr1

@app.route("/today2")
def today2():
    return todayStr2

@app.route("/today3")
def today3():
    return todayStr3

@app.route("/today4")
def today4():
    return todayStr4

@app.route('/searchPrecise', methods=['POST', 'GET'])
def search_precise():
    if request.method == 'POST':
        if validate_json(request.data):
            return search_keyword(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

@app.route('/searchCoarse', methods=['POST', 'GET'])
def search_coarse():
    if request.method == 'POST':
        if validate_json(request.data):
            return search_serving(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

@app.route('/signup', methods=['POST', 'GET'])
def user_signup():
    if request.method == 'POST':
        if validate_json(request.data):
            return sign_up(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

@app.route('/login', methods=['POST', 'GET'])
def user_login():
    if request.method == 'POST':
        if validate_json(request.data):
            return login(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

@app.route('/upload_data', methods=['POST', 'GET'])
def upload_data():
    if request.method == 'POST':
        if validate_json(request.data):
            return upload(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

@app.route('/download_data', methods=['POST', 'GET'])
def download_data():
    if request.method == 'POST':
        if validate_json(request.data):
            return download(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

#return true if the string can be load into json object, otherwise false
def validate_json(data):
    try:
        json_object = json.loads(data.decode('utf-8'))
    except ValueError:
        print("Not valid Json")
        return False
    return True

#update user suggestion on daily basis
def update_today():
    jsonArray = json.loads(random_today())
    global todayStr1
    global todayStr2
    global todayStr3
    global todayStr4
    todayStr1 = json.dumps(jsonArray[0])
    todayStr2 = json.dumps(jsonArray[1])
    todayStr3 = json.dumps(jsonArray[2])
    todayStr4 = json.dumps(jsonArray[3])
    print("Updated today's suggestion at" , datetime.datetime.now())

sched = BackgroundScheduler(daemon=True)
sched.add_job(update_today,'cron',day=1,jitter=0)
sched.start()
update_today()

if __name__ == "__main__":
    app.run(host='127.0.0.1')
