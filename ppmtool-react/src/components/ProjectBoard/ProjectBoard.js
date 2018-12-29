import React, { Component } from 'react';
import Backlog from './Backlog';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { getBacklog } from '../../actions/projectTaskActions';
import { Link } from 'react-router-dom';

class ProjectBoard extends Component {
	constructor() {
		super();
		this.state = {
			errors: {},
		};
	}

	componentDidMount() {
		this.props.getBacklog(this.props.match.params.id, this.props.history);
	}

	componentWillReceiveProps(nextProps) {
		if (nextProps.errors) {
			this.setState({ errors: nextProps.errors });
		}
	}

	render() {
		const { id } = this.props.match.params;
		const { project_tasks } = this.props.backlog;
		const { errors } = this.state;

		let BoardContent;

		const boardAlgorithm = (errors, project_tasks) => {
			if (project_tasks.length < 1) {
				if (errors.projectNotFound) {
					return (
						<div className="alert alert-danger text-center" role="alert">
							{errors.projectNotFound}
						</div>
					);
				} else {
					return (
						<div className="alert alert-info text-center" role="alert">
							No Project Tasks on this board
						</div>
					);
				}
			} else {
				return <Backlog projectTasks={project_tasks} />;
			}
		};

		BoardContent = boardAlgorithm(errors, project_tasks);

		return (
			<div className="container">
				<Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
					<i className="fas fa-plus-circle"> Create Project Task</i>
				</Link>
				<br />
				<hr />
				{BoardContent}
			</div>
		);
	}
}

ProjectBoard.propTypes = {
	getBacklog: PropTypes.func.isRequired,
	backlog: PropTypes.object.isRequired,
};

const mapStateToProps = state => ({
	backlog: state.backlog,
});

export default connect(
	mapStateToProps,
	{ getBacklog }
)(ProjectBoard);
