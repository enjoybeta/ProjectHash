from flask import Flask,request
import json
from search1_serving import search_serving
from search2_keyword import search_keyword
from user_manager import sign_up
from user_manager import login
from user_manager import upload
from user_manager import download

app = Flask(__name__)

@app.route("/")
def guidance():
    return "/today1 /today2 /today3 /today4 /search"
    
@app.route("/today1")
def today1():
    with open ("../Database/examples/1.json", "r") as myfile:
        data=myfile.readline()
    return data
    
@app.route("/today2")
def today2():
    with open ("../Database/examples/2.json", "r") as myfile:
        data=myfile.readline()
    return data
    
@app.route("/today3")
def today3():
    with open ("../Database/examples/3.json", "r") as myfile:
        data=myfile.readline()
    return data
    
@app.route("/today4")
def today4():
    with open ("../Database/examples/4.json", "r") as myfile:
        data=myfile.readline()
    return data
    
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

if __name__ == "__main__":
    app.run(host='127.0.0.1')
