import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class ProjectTask extends Component {
	render() {
		return (
			<div className="card mb-1 bg-light">
				<div className="card-header text-primary" />
				<div className="card-body bg-light">
					<h4 className="card-title">{this.props.projectTask.summary}</h4>
					<p className="card-text text-truncate ">{this.props.projectTask.acceptanceCriteria}</p>
					<Link
						to={`/updateProjectTask/${this.props.projectTask.projectIdentifier}/${
							this.props.projectTask.projectSequence
						}`}
						className="btn btn-primary"
					>
						View/ Update
					</Link>

					<button className="btn btn-danger ml-4">Delete</button>
				</div>
			</div>
		);
	}
}
export default ProjectTask;
