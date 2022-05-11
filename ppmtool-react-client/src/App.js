import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Header from './components/Layout/Header';
import Dashboard from './components/Dashboard';
import AddProject from './components/Project/AddProject';
import UpdateProject from './components/Project/UpdateProject';
import { Provider } from 'react-redux';
import store from './store';

function App() {
  return (
    <Provider store={store}>
      <Router>
        <Header />
        <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/addProject" element={<AddProject />} />
          <Route path="/updateProject/:id" element={<UpdateProject />} />
        </Routes>
      </Router>
    </Provider>
  );
}

export default App;
