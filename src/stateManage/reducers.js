import { combineReducers } from 'redux';
import { changeTipsContent } from '../components/EntryPage/NavigationTips/reducers/changeTipsContent';
const reducers = combineReducers({
    changeTipsContent: changeTipsContent,
})
export default reducers