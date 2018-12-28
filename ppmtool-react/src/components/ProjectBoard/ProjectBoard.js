import React, { Component } from 'react';
import Backlog from './Backlog';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { getBacklog } from '../../actions/projectTaskActions';
import { Link } from 'react-router-dom';

class ProjectBoard extends Component {
	componentDidMount() {
		this.props.getBacklog(this.props.match.params.id, this.props.history);
	}

	render() {
		const { id } = this.props.match.params;
		const { project_tasks } = this.props.backlog;

		return (
			<div className="container">
				<Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
					<i className="fas fa-plus-circle"> Create Project Task</i>
				</Link>
				<br />
				<hr />
				<Backlog projectTasks={project_tasks} />
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
