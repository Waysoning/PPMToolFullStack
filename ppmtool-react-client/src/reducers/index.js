import { combineReducers } from 'redux';
import errorReducer from './errorReducer';

export default combineReducers({
  // Add reducers here
  errors: errorReducer,
});
