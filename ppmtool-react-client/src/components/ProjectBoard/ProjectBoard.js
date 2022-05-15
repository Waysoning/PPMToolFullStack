import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Backlog from './Backlog';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getBacklog } from '../../actions/backlogActions';

class ProjectBoard extends Component {
  // constructor to hanle the errors

  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }

  render() {
    const { id } = this.props.match.params;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog />
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  getBacklog: PropTypes.func.isRequired,
  backlog: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog.backlog,
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
