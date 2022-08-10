import React from 'react'
import Grid from '@material-ui/core/Grid'

import BikePath from './BikePath'

const PathList = props => {
  var paths = props.paths
  console.log(paths)
  return <div>
     <Grid container spacing={2} >´
      {paths !== undefined && paths.map(path => {
        if (path !== undefined) {
          return <Grid item xs={12}>
              <BikePath path={path}/>
            </Grid>
        }
      })}
    </Grid>
  </div>
}

export default PathList