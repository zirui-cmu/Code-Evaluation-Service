from flask import Flask, request
import requests

sqlServiceHostName = "http://13.82.29.100"
pythonServiceHostName = ["http://104.154.35.36/py/eval", "http://13.82.29.100/py/eval"]

app = Flask(__name__, static_folder='site', static_url_path='')

@app.route("/", methods=['GET'])
def handle():
    return app.send_static_file("index.html")

@app.route("/sql", methods=['GET', 'POST'])
def handleSQL():
    if request.method == 'POST':
        operation = request.form['operation']
        query = request.form['query']
        print (query)
        if operation == "read":
            response = requests.post("http://13.82.29.100/sql/read", data={'code': query.encode('utf-8')})
        if operation == "write":
            response = requests.post("http://13.82.29.100/sql/write", data={'code': query.encode('utf-8')})
        return response.text
    else:
        return app.send_static_file("sql.html")

@app.route("/python", methods=['GET', 'POST'])
def handlePython():
    if request.method == 'POST':
        code = request.form['code']
        global count
        count[0] += 1
        if count[0]%2 == 0:
            response = requests.post(pythonServiceHostName[0], data={'code': code.encode('utf-8')})
        else:
            response = requests.post(pythonServiceHostName[1], data={'code': code.encode('utf-8')})
        return response.text
    else:
        return app.send_static_file("python.html")

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000, threaded=True)
    global count

