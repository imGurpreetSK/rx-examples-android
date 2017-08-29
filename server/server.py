from flask import Flask, jsonify
from lists import list_fb_users, list_devrant_users, list_snap_users

app = Flask(__name__)


@app.route('/')
def home():
    return '<a align="center" href=\"https://www.github.com/GurpreetSK95/rx-examples-android\">RX-EXAMPLES-ANDROID</a>'


@app.route('/get_fb_users')
def return_fb_users_list():
    return jsonify(list_fb_users)


@app.route('/get_snap_users')
def return_snap_users_list():
    return jsonify(list_snap_users)


@app.route('/get_devrant_users')
def return_devrant_users_list():
    return jsonify(list_devrant_users)


if __name__ == '__main__':
    app.run()
