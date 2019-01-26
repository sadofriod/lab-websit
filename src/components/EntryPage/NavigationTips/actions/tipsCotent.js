export const GET_TIPS_CONTENT = 'getTipsContent',
            LEAVE_NAVIGATOR = 'leaveNavigator'
export let getTipsContent = state => ({
    type: 'getTipsContent',
    content: '',
    routeName: state.routeName,
    entry:state.entry,
    url: state.url,
});

