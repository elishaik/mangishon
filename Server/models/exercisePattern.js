var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('ExercisePattern', new Schema({
    challenge: { type: Schema.Types.ObjectId, ref: 'Challenge' },
    mistakings: [{ type: Schema.Types.ObjectId, ref: 'Mistaking' }],
}));
