const express = require('express')
const app = express();
var bodyParser = require('body-parser');
var cors = require('cors');
var path = require("path");


const port = 3000;

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.static('public_html'));

app.listen(port, function () {
  console.log('Web server listening on port 3000');
})

app.get('/', (req, res) => res.send('Hello World!'))

app.get("/loginPage", function (request, response) {
  response.sendFile(path.join(__dirname + '/index.html'));
});

app.post('/login', (request, response) => {
  var email = request.body.email;
  var password = request.body.password;

  if (email === "abc@gmail.com") {
    if (password === "1") {
      response.json("Login success!");
    }
    else {
      response.json("Wrong password!");
    }
  }
  else {
    response.json("Wrong email!");
  }
});

