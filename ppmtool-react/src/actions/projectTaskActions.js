import axios from 'axios';
import { GET_BACKLOG, GET_ERRORS, GET_PROJECT_TASK } from './types';

export const getBacklog = backlogIdentifier => async dispatch => {
	try {
		const response = await axios.get(`/api/backlog/${backlogIdentifier}`);
		dispatch({
			type: GET_BACKLOG,
			payload: response.data,
		});
	} catch (error) {
		dispatch({
			type: GET_ERRORS,
			payload: error.response.data,
		});
	}
};

export const addProjectTask = (projectIdentifier, projectTask, history) => async dispatch => {
	try {
		await axios.post(`/api/backlog/${projectIdentifier}`, projectTask);
		history.push(`/projectBoard/${projectIdentifier}`);
		dispatch({
			type: GET_ERRORS,
			payload: {},
		});
	} catch (error) {
		dispatch({
			type: GET_ERRORS,
			payload: error.response.data,
		});
	}
};

export const getProjectTask = (backlogIdentifier, projectTaskSequence) => async dispatch => {
	try {
		const response = await axios.get(`/api/backlog/${backlogIdentifier}/${projectTaskSequence}`);
		dispatch({
			type: GET_PROJECT_TASK,
			payload: response.data,
		});
	} catch (error) {
		dispatch({
			type: GET_ERRORS,
			payload: error.response.data,
		});
	}
};

export const updateProjectTask = (
	backlogIdentifier,
	projectTaskSequence,
	updatedProjectTask,
	history
) => async dispatch => {
	try {
		await axios.patch(`/api/backlog/${backlogIdentifier}/${projectTaskSequence}`, updatedProjectTask);
		history.push(`/projectBoard/${backlogIdentifier}`);
		dispatch({
			type: GET_ERRORS,
			payload: {},
		});
	} catch (error) {
		dispatch({
			type: GET_ERRORS,
			payload: error.response.data,
		});
	}
};
