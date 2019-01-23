import { combineReducers } from 'redux';
import { changeTipsContent } from '../components/HomePage/NavigationTips/reducers/changeTipsContent';
const reducers = combineReducers({
    changeTipsContent: changeTipsContent,
})
export default reducers