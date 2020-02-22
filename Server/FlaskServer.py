""" 
Author: Sean Moylan G00299424
Adapted partly from: https://android.jlelse.eu/handmade-backend-for-android-app-using-python-flask-framework-b173ba2bb3aa

"""

# Imports
import json
import flask as fl
from flask import request, jsonify, make_response
from pymongo import MongoClient



client = MongoClient()


# Flask instance
app = fl.Flask(__name__)



# Root directory
@app.route("/")
def index():
    return "Server running..."

# GET all users
@app.route('/users')
def get_users():
    with open("jsonfiles/user.json") as f:
        data = json.loads(f.read())
    return make_response(data)

# POST specific user 
@app.route('/users/<user>')
def create_user(user):
    return "Hello {user}"

# GET all locations
@app.route('/locations')
def get_locations():
    with open("jsonfiles/location.json") as f:
        data = json.loads(f.read())
    return make_response(data)

# GET all locations
@app.route('/login')
def login():
    with open("jsonfiles/login.json") as f:
        data = json.loads(f.read())
    return make_response(data)



# GET location
@app.route('/locations/<locationId>')
def get_location(locationId):
    return "Specific location %s" %locationId


#app.run(debug=True)
