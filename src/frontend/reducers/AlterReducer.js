import { alterConstants } from '../constants/AlterConstants';

export const alert = (state = {}, action) => {
    switch (action.type){
        case alterConstants.SUCCESS:
            return {
                type: 'alert-success',
                message: action.message
            }
        case alterConstants.ERROR:
            return {
                type: 'alert-danger',
                message: action.message
            }
        case alterConstants.CLEAR:
            return {};
        default:
            return state            
    }
}