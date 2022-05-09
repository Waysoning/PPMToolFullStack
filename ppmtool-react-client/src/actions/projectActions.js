import axios from 'axios';
import { GET_ERRORS } from './types';

export const createProject = (projectData) => async (dispatch) => {
  try {
    await axios.post('http://localhost:8080/api/project', projectData);
    window.location.href = '/dashboard';
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
