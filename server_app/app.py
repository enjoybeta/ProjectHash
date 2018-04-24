from flask import Flask,request
import json
from apscheduler.schedulers.background import BackgroundScheduler
from search1_serving import search_serving
from search2_keyword import search_keyword
from user_manager import sign_up,login,upload,download
from random_today import random_today

app = Flask(__name__)
sched = BackgroundScheduler(daemon=True)
sched.add_job(updateTodaysRecipe,'cron',CronTrigger.from_crontab('0 0 * * *'),jitter=0)
sched.start()
today1 = ""
today2 = ""
today3 = ""
today4 = ""

@app.route("/")
def guidance():
    return "/today1 /today2 /today3 /today4 /search"
    
@app.route("/today1")
def today1():
    return today1
    
@app.route("/today2")
def today2():
    return today2
    
@app.route("/today3")
def today3():
    return today3
    
@app.route("/today4")
def today4():
    return today4
    
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

def updateTodaysRecipe(data):
    jsonArray = json.loads(random_today())
    today1 = jsonArray[0]
    today2 = jsonArray[1]
    today3 = jsonArray[2]
    today4 = jsonArray[3]

if __name__ == "__main__":
    app.run(host='127.0.0.1')
