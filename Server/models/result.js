var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('Result', new Schema({
    exercise: { type: Schema.Types.ObjectId, ref: 'Exercise' },
    time: Number,
    difficultyLevel: Number,
    successes: Number,
    failures: Number,
    dateTime: Date
}));
