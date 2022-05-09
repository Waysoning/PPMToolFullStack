import axios from 'axios';
import { GET_ERRORS } from './types';

export const createProject = (projectData, history) => async (dispatch) => {
  try {
    await axios.post('/api/projects', projectData);
    history.push('/dashboard');
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
