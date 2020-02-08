""" 
Author: Sean Moylan G00299424
Adapted partly from: https://android.jlelse.eu/handmade-backend-for-android-app-using-python-flask-framework-b173ba2bb3aa

"""

# Imports
import flask as fl
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
    return "All users"

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

# POST 
@app.route('/api/post_some_data', methods=['POST'])
def get_text_prediction():
    """
    predicts requested text whether it is ham or spam
    :return: json
    """
    json = request.get_json()
    print(json)
    if len(json['text']) == 0:
        return jsonify({'error': 'invalid input'})

    return jsonify({'you sent this': json['text']})
    
#app.run(debug=True)
