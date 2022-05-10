import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';

export default combineReducers({
  // Add reducers here
  errors: errorReducer,
  project: projectReducer,
});
