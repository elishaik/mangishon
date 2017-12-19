var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('SuccessParameter', new Schema({
    formula: String
}));
