import React, { Component } from 'react';

class ProjectTask extends Component {
  render() {
    const { task } = this.props;
    let priorityString = '';
    let priorityClass = '';

    if (task.priority === 3) {
      priorityString = 'Low';
      priorityClass = 'bg-success text-light';
    } else if (task.priority === 2) {
      priorityString = 'Medium';
      priorityClass = 'bg-warning text-light';
    } else if (task.priority === 1) {
      priorityString = 'High';
      priorityClass = 'bg-danger text-light';
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {task.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{task.summary}</h5>
          <p className="card-text text-truncate ">{task.acceptanceCriteria}</p>
          <a href="" className="btn btn-primary">
            View / Update
          </a>
          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}

export default ProjectTask;
