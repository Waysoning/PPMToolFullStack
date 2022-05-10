import axios from 'axios';
import { GET_ERRORS, GET_PROJECTS } from './types';

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

export const getProjects = () => async (dispatch) => {
  try {
    const res = await axios.get('http://localhost:8080/api/project/all');
    dispatch({
      type: GET_PROJECTS,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
