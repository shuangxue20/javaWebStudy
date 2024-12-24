from flask import Blueprint, render_template, request, redirect, url_for
from .models import User, Score
from . import db

main = Blueprint('main', __name__)

@main.route('/')
def index():
    users = User.query.all()
    return render_template('index.html', users=users)

@main.route('/submit_score', methods=['POST'])
def add_score():
    user_id = request.form.get('user_id')
    score = request.form.get('score')
    new_score = Score(user_id=user_id, score=score)
    db.session.add(new_score)
    db.session.commit()
    return redirect(url_for('main.index'))