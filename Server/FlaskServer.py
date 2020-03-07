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
    # Save a user
    # User(username="cheezy23", email="seanmoylan1@icloud.com", password="1234").save()
    Location(latitude="1.2523523", longitude="2.252334", description="somthing", title="Stairs", spot_type="2").save()
    return "Server running..."

# GET all users
@app.route('/users')
def get_users():
    user = User.objects()

    return jsonify(user)


# Get user 
@app.route('/users/<user>')
def get_user(user):
    user1 = User.objects.get(username=user)
    return jsonify(user1)


# Create User 
@app.route('/users/create', methods=['POST'])
def create_user():

    # Get the json data from the post request
    user_json = request.get_json()

    # Try saving to the database, if there is an error 

    newuser = User(username = user_json['username'], email = user_json['email'], password = user_json['password']).save()
    return jsonify(newuser)


# Login User 
@app.route('/users/login', methods=['POST'])
def login_user():

    # Get the json data from the post request
    user_json = request.get_json()
    print(user_json['username'])

    try:
        user_credentials = User.objects.get(username=user_json['username'])
    except:
        print("No such user")
        return "Did not match"
    

    # Check that the user password matched the stored password
    if(user_credentials.password == user_json["password"] ):
        # user can login
        return jsonify(user_credentials)
    else:
        return ("Incorrect username or password")
    
    
    

    
    

# GET all locations
@app.route('/locations')
def get_locations():

    locations = Location.objects.get()
    return jsonify(locations)


# GET location
@app.route('/locations/<locationId>')
def get_location(locationId):
    return "Specific location %s" %locationId





# DATABASE Documents

class Location(db.Document):
    latitude = db.FloatField(unique=True, required=True)
    longitude = db.FloatField(required=True)
    title = db.StringField(required=True)
    description = db.StringField()
    spot_type = db.StringField()


class User(db.Document):
    username = db.StringField(unique=True, required=True)
    email = db.EmailField(required=True)
    password = db.StringField(required=True)
