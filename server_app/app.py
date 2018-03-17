from flask import Flask,request
import json

app = Flask(__name__)

@app.route("/")
def guidance():
    return "/today1 /today2 /today3 /today4 /search"
    
@app.route("/today1")
def today1():
    with open ("../Database/return.json", "r") as myfile:
        data=myfile.readline()
    return data
    
@app.route("/today2")
def today2():
    with open ("../Database/return2.json", "r") as myfile:
        data=myfile.readline()
    return data
    
@app.route("/today3")
def today3():
    return "today3"
    
@app.route("/today4")
def today4():
    return "today4"
    
@app.route('/search', methods=['POST', 'GET'])
def search_data():
    if request.method == 'POST':
        if valid_search(request.data):
            return process_search(request.data)
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
        print("Not Json file")
        return False
    return True
    
def process_search(data):
    return data

if __name__ == "__main__":
    app.run(host='0.0.0.0',ssl_context=('cert.pem', 'key.pem'))