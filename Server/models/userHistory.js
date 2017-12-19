var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('UserHistory', new Schema({
    user: { type: Schema.Types.ObjectId, ref:'User' },
    history:[{ type: Schema.Types.ObjectId, ref: 'Result' }]
}));
