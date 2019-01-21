import { combineReducers } from 'redux';
const gearIcon = (state = '', action) => {
    switch (action.type) {
        case 'URL':
            console.log(action.url);
            break;
        default:
            return state;
    }
}
const reducers = combineReducers({
    gearIcon: gearIcon
})
export default reducers