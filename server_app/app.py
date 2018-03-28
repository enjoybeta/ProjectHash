from flask import Flask,request
import json
from search1_keyword import search_serving
from search2_keyword import search_keyword

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
    
@app.route('/searchRecipe', methods=['POST', 'GET'])
def search_data():
    if request.method == 'POST':
        if valid_search(request.data):
            return process_search_by_name(request.data)
        else:
            error = 'Invalid input data'
            return error
    else:
        error = 'Only accepts POST method'
        return error

def valid_search(data):
    try:
        json_object = json.loads(data.decode('utf-8'))
    except ValueError:
        print("Not valid Json")
        return False
    return True
    
def process_search_by_name(data):
    return search_keyword2(data)

if __name__ == "__main__":
    app.run(host='127.0.0.1')
