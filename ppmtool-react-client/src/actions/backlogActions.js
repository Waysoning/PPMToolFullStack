import axios from 'axios';

export const addProjectTask =
  (backlog_id, project_task, history) => async (dispatch) => {
    try {
      const res = await axios.post(`/api/backlog/${backlog_id}`, project_task);
      dispatch({
        type: 'ADD_PROJECT_TASK',
        payload: res.data,
      });
      history.push(`/projectBoard/${backlog_id}`);
    } catch (error) {
      console.log(error);
    }
  };
