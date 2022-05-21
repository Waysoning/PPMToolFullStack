import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

const SecureRoute = ({
  component: Component,
  security: { validToken },
  ...rest
}) => (
  <Route
    {...rest}
    render={(props) =>
      validToken ? <Component {...props} /> : <Redirect to="/login" />
    }
  />
);

SecureRoute.propTypes = {
  security: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  security: state.security,
});

export default connect(mapStateToProps)(SecureRoute);
