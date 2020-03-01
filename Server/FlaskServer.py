""" 
Author: Sean Moylan G00299424
Adapted partly from: https://android.jlelse.eu/handmade-backend-for-android-app-using-python-flask-framework-b173ba2bb3aa

"""

# Imports
import json
import flask as fl
from flask import request, jsonify, make_response
from flask_mongoengine import MongoEngine




# Flask instance
app = fl.Flask(__name__)

# Connect to mongo and initialize
db = MongoEngine()
db.init_app(app)

# Root directory
@app.route("/")
def index():
    
    User(username="cheezy23", email="seanmoylan1@icloud.com", password="1234").save()

    return "Server running..."

# GET all users
@app.route('/users')
def get_users():
    user = User.objects.get(username="cheezy23")

    with open("jsonfiles/user.json") as f:
        data = json.loads(f.read())
    return jsonify(user)

# POST specific user 
@app.route('/users/<user>')
def create_user(user):
    username = user
    user = User.objects.get(username=username)
    return jsonify(user)

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





# DATABASE




class Location(db.Document):
    latitude = db.FloatField(required=True)
    longitude = db.FloatField(required=True)
    title = db.StringField(required=True)
    description = db.StringField()
    spot_type = db.StringField()


class User(db.Document):
    username = db.StringField(unique=True, required=True)
    email = db.EmailField(unique=True, required=True)
    password = db.StringField(required=True)
