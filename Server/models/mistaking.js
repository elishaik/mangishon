var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('Mistaking', new Schema({
    challenge1: { type: Schema.Types.ObjectId, ref: 'Challenge' },
    challenge2: { type: Schema.Types.ObjectId, ref: 'Challenge' },
    connectionTypes: [{ type: String, enum: ['Word', 'Sentence', 'Image', 'Audio'] }],
    dyslexiaTypes: [{ type:String }]
}));
