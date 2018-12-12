import { GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from '../actions/types';

const initialState = {
	projects: [],
	project: {},
};

export default function(state = initialState, action) {
	switch (action.type) {
		case GET_PROJECTS:
			console.log(action.payload);
			return {
				...state,
				projects: action.payload,
			};
		case GET_PROJECT:
			console.log(action.payload);
			return {
				...state,
				project: action.payload,
			};
		case DELETE_PROJECT:
			console.log(action.payload);
			return {
				...state,
				projects: state.projects.filter(p => p.projectIdentifier !== action.payload),
			};
		default:
			return state;
	}
}
