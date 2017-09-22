from flask import Flask, request
import subprocess
import json

app = Flask(__name__, static_url_path='')

@app.route("/py/eval", methods=['GET', 'POST'])
def handle():
    if request.method == 'POST':

        # Implementation goes here.
        #
        # Both stdout and stderr should be captured.
        # {"stdout": "<output_from_stdout>", "stderr": "<output_from_stderr>"}

        ### BEGIN STUDENT CODE ###
        code = request.form['code']
        # print(code)
        p = subprocess.Popen('python', stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                             stderr=subprocess.PIPE,shell = True)
        out, err = p.communicate(input=code)
        # print(out)
        # print(err)
        response = json.dumps({"stdout": out, "stderr": err})
        return response
        ### END STUDENT CODE ###

if __name__ == '__main__':
    app.run(threaded=True, debug=True, host="0.0.0.0", port=6000)
