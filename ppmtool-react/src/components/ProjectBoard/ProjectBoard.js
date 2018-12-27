import React, { Component } from 'react';
import Backlog from './Backlog';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { getBacklog } from '../../actions/projectTaskActions';

class ProjectBoard extends Component {
	componentDidMount() {
		this.setState({ project_tasks: this.props.getBacklog(this.props.match.params.id, this.props.history) });
	}

	render() {
		return (
			<div className="container">
				<a href="#" className="btn btn-primary mb-3">
					<i className="fas fa-plus-circle"> Create Project Task</i>
				</a>
				<br />
				<hr />
				<Backlog />
			</div>
		);
	}
}

ProjectBoard.propTypes = {
	getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = state => {};

export default connect(
	null,
	{ getBacklog }
)(ProjectBoard);
