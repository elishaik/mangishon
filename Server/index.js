let express = require('express');
let app = express();
let bodyParser = require('body-parser');
let mongoose = require('mongoose');
var morgan = require('morgan');
global.config = require('./config/config');

//parse url params and log details
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(morgan('dev'));

//connect to mongo
var connectionString = global.config.connectionString;
mongoose.connect(connectionString);
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  console.log('Connected to db successfully. url is: ' + connectionString)
});

app.get('/', function(req, res){
    res.send('hello world');
});

//connect the api routes
app.use(require('./controllers'));

app.listen(3000, function(){
    console.log('App running on 3000');
});
