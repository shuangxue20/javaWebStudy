import os

class Config:
    SECRET_KEY = os.urandom(24)
    SQLALCHEMY_DATABASE_URI = 'sqlite:///your_app.db'  # For SQLite
    # SQLALCHEMY_DATABASE_URI = 'mongodb://localhost:27017/your_app'  # For MongoDB
    SQLALCHEMY_TRACK_MODIFICATIONS = False