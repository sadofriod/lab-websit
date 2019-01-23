import * as actions from '../actions/tipsCotent';
let routeName = '';
export const changeTipsContent = (state = actions.getTipsContent, action) => {
    switch (action.routeName) {
        case 'home':
            action.content = 'You will understand more information about IMUT-Rookie-LAB in this page.It may include struct of our team and our history'
            return action;
        case 'about':
            action.content = 'You will know ways of contacting us,our address and major business in this module';
            return action;
        case 'tools':
            action.content = 'We provide some tools for quickly build a websit.Them also used for build some simple application in android or IOS'
            return action;
        case 'blog':
            action.content = 'We record problems which were done or not solve, and display by blog module. It may can help you find solution if you meet same problem.'
            return action;
        case 'factory':
            action.content = 'We provide some solution for business user.Example in Iot 、web portals and small E-commerce we can provide runable template'
            return action;
        case 'repositories':
            action.content = 'You will see our open origin project that hosted in Github or our servers';
            return action;
        case 'private forum':
            action.content = 'This module will used for members communication';
            return action;
        case 'public forum':
            action.content = 'This module will used for all of user communication';
            return action;
        default:
            return state;

    }



    return state;
}
