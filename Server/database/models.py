from mongoengine.fields import *
from mongoengine.connection import connect


connect("spot_finder_db")

class Location():
    latitude = FloatField(required=True)
    longitude = FloatField(required=True)
    title = StringField(required=True)
    description = StringField()
    spot_type = StringField()


class User():
    username = StringField(unique=True, required=True)
    email = EmailField(unique=True, required=True)
    password = StringField(required=True)