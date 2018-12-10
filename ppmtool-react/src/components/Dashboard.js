import React, { Component } from 'react';
import ProjectItem from './Project/ProjectItem';
import CreateProjectButton from './Project/CreateProjectButton';
import { connect } from 'react-redux';
import { getAllProjects } from '../actions/projectActions';
import PropTypes from 'prop-types';

class Dashboard extends Component {
	componentDidMount() {
		this.setState({ projects: this.props.getAllProjects() });
	}

	render() {
		const projects = this.props.project.projects.map(p => {
			return (
				<ProjectItem
					key={p.id}
					projectName={p.projectName}
					projectIdentifier={p.projectIdentifier}
					description={p.description}
				/>
			);
		});

		return (
			<div className="projects">
				<div className="container">
					<div className="row">
						<div className="col-md-12">
							<h1 className="display-4 text-center">Projects</h1>
							<br />
							<CreateProjectButton />
							<br />
							<hr />
							{projects}
						</div>
					</div>
				</div>
			</div>
		);
	}
}

Dashboard.propTypes = {
	project: PropTypes.object.isRequired,
	getAllProjects: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
	project: state.project,
});

export default connect(
	mapStateToProps,
	{ getAllProjects }
)(Dashboard);
