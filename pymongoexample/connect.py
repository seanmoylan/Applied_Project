from flask import Flask
from flask.ext.mongoalchemy import MongoAlchemy

app = Flask(__name__)

app.config('MONGOALCHEMY_DATABASE') = 'connect'
app.config('MONGOALCHEMY_CONNECTION_STRING')