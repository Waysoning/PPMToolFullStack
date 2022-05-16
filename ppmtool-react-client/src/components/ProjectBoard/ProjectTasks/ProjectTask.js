import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { deleteProjectTask } from '../../../actions/backlogActions';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

class ProjectTask extends Component {
  onDeleteClick(backlog_id, pt_id) {
    this.props.deleteProjectTask(backlog_id, pt_id);
  }

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
          <Link
            to={`/updateProjectTask/${task.projectIdentifier}/${task.projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>
          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              task.projectIdentifier,
              task.projectSequence
            )}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
