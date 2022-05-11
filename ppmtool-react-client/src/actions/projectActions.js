import axios from 'axios';
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from './types';

export const createProject = (projectData, history) => async (dispatch) => {
  try {
    await axios.post('http://localhost:8080/api/project', projectData);
    history.push('/dashboard');
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
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

export const getProject = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(`http://localhost:8080/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });
  } catch (err) {
    history.push('/dashboard');
  }
};
