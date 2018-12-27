import axios from 'axios';
import { GET_BACKLOG, GET_ERRORS } from './types';

export const getBacklog = (backlogIdentifier, history) => async dispatch => {
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
