import { configureStore } from '@reduxjs/toolkit';
import thunk from 'redux-thunk';
import { rootReducer } from './reducers/index';

const initialState = {};
const middleware = [thunk];

let store;

if (window.navigator.userAgent.includes('Chrome')) {
  store = configureStore({
    reducer: rootReducer,
    middleware,
    devTools: true,
    preloadedState: initialState,
  });
} else {
  store = configureStore({
    reducer: rootReducer,
    middleware,
    preloadedState: initialState,
  });
}

export default store;
