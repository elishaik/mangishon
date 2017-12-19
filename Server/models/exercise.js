var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('Exercise', new Schema({
    exercisePattern: { type: Schema.Types.ObjectId, ref: 'ExercisePattern' },
    exerciseTypes: { type: String, enum: ['Word', 'Sentence', 'Image', 'Audio'] },
    SuccessParameter: { type: Schema.Types.ObjectId, ref: 'SuccessParameter' }
}));
