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
    with open("users/user.json") as f:
        data = json.loads(f.read())
    return make_response(data)

# GET specific user 
@app.route('/users/<user>')
def get_user(user):
    return "Hello %s!" % user

# GET all locations
@app.route('/locations')
def get_locations():
    return "All locations"

# GET location
@app.route('/locations/<locationId>')
def get_location(locationId):
    return "Specific location %s" %locationId


#app.run(debug=True)
