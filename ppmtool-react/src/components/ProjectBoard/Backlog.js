import React, { Component } from 'react';
import ProjectTask from './ProjectTask/ProjectTask';

class Backlog extends Component {
	render() {
		const { projectTasks } = this.props;

		const tasks = projectTasks.map(task => {
			return <ProjectTask key={task.id} projectTask={task} />;
		});
		let todoItems = [];
		let inprogressItems = [];
		let doneItems = [];

		tasks.forEach(task => {
			switch (task.props.projectTask.status) {
				case 'TODO':
					todoItems.push(task);
					break;
				case 'INPROGRESS':
					inprogressItems.push(task);
					break;
				case 'DONE':
					doneItems.push(task);
					break;
				default:
					break;
			}
		});

		return (
			<div className="container">
				<div className="row">
					<div className="col-md-4">
						<div className="card text-center mb-2">
							<div className="card-header bg-secondary text-white">
								<h3>TO DO</h3>
								{todoItems}
							</div>
						</div>
					</div>
					<div className="col-md-4">
						<div className="card text-center mb-2">
							<div className="card-header bg-primary text-white">
								<h3>In Progress</h3>
								{inprogressItems}
							</div>
						</div>
					</div>
					<div className="col-md-4">
						<div className="card text-center mb-2">
							<div className="card-header bg-success text-white">
								<h3>Done</h3>
								{doneItems}
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}
export default Backlog;
